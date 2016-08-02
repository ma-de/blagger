package blagger

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(PostService)
@Mock([Post])
class PostServiceSpec extends Specification {

	PostService postService

	def setup() {
		postService = new PostService()
	}

	void "test get all posts"() {
		given:
		new Post(title: 'test1', email: 'test@test.com', content: 'test').save() 

		when:
		def postList = postService.getAllPosts([])

		then:
		postList
		postList[0].title == 'test1'
        postList[0].email == 'test@test.com'
        postList[0].content == 'test'
	}

}
