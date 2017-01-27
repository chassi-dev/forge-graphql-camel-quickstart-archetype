package ${package};

import graphql.ExecutionResult;
import graphql.GraphQL;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ExampleController {

	@RequestMapping(value = "/graphql", method = RequestMethod.GET)
	public Object graphQLQuery(@RequestParam("query") String query) {
		ExecutionResult result = new GraphQL(com.example.Query.querySchema).execute(query);
		List errors = result.getErrors();
		if(errors.size() > 0) return errors;
		return result.getData();
	}

}