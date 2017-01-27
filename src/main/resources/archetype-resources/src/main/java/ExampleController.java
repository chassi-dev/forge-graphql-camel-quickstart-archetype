package ${package};

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExampleController {

	@RequestMapping(value = "/graphql", method = RequestMethod.GET)
	public void graphQLQuery(@RequestParam("query") String query) {
//		Graphql executed = new GraphQL(Query.querySchema).execute(query)
//						["data": executed.data, "errors": executed.errors] as Map
	}

	@RequestMapping(value = "/graphql")
	public void graphQLMutation(@RequestBody String body){
//		def executed = new GraphQL(Query.querySchema).execute(body)
//						["data": executed.data, "errors": executed.errors] as Map
	}
}
