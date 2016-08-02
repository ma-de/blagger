package blagger

import grails.test.mixin.*
import spock.lang.Specification

@TestFor(PostController)
@Mock([PostService, TagService, Post, Tag])
class PostControllerSpec extends Specification {

    def 'list returns the blag posts'(){
        given:
        controller.postService = createMockPostService()
        controller.tagService = createMockTagService()

        when:
        def model = controller.index()

        then:
        model.posts
        model.posts[0].title == 'test1'
    }

    def 'list returns the blag posts in the correct order'(){
        given:
        controller.postService = createMockPostService()
        controller.tagService = createMockTagService()

        when:
        def model = controller.index()

        then:
        model.posts
        model.posts[0].title == 'test1'
        model.posts[1].title == 'test2'
    }

    def 'create adds a new blag post and redirects'() {
        given:
        controller.params.title = 'test'
        controller.params.email = 'test@test.com'
        controller.params.content = 'test'

        when:
        controller.savePost()
        def posts = Post.list()

        then:
        posts
        posts[0].title == 'test'
        posts[0].email == 'test@test.com'
        posts[0].content == 'test'
        response.redirectUrl.endsWith('/index')
    }

    def 'invalid post is not saved and create page is rendered with the errors'() {
        given:
        controller.params.title = ''
        controller.params.email = 'test@test.com'
        controller.params.content = 'test'

        when:
        controller.savePost()

        then:
        !Post.count

        and:
        with (controller.modelAndView) {
            viewName == '/post/create'
            model.post.hasErrors()
        }
    }

    def 'Post new post with one tag'(){
        given:
        controller.params.title = 'test'
        controller.params.email = 'test@test.com'
        controller.params.content = 'test'
        controller.params.tag = new Tag(name: 'test1')

        when:
        controller.savePost()
        def posts = Post.list()

        then:
        posts
        posts[0].title == 'test'
        posts[0].email == 'test@test.com'
        posts[0].content == 'test'
        posts[0].tag.name == 'test1'
        response.redirectUrl.endsWith('/index')
    }



    def 'List of all post filtered by given tag name'(){
        given:
        controller.postService = createMockPostWithTagService()
        controller.tagService = createMockTagService()
        controller.params.id = 'tag1'

        when:
        controller.list()
        
        then:
        model.posts
        model.posts[0].title == 'test1'
        model.posts[0].tag.name == 'tag1'
    }

    /** Mock helper for services */
    private TagService createMockTagService(){
        TagService mock = Mock(TagService)
        mock.metaClass.listAllTags = { -> return [:] }
        return mock
    }

    /** Mock helper for services */
    private PostService createMockPostService(){
        def post1 = new Post(title: 'test1', email: 'test@test.com', content: 'test').save() 
        def post2 = new Post(title: 'test2', email: 'test@test.com', content: 'test').save()
        PostService mock = Mock(PostService)
        mock.getAllPosts(_) >> [post1, post2]        
        return mock
    }


    /** Mock helper for services */
    private PostService createMockPostWithTagService(){
        def post1 = new Post(title: 'test1', email: 'test@test.com', content: 'test', tag: new Tag(name: 'tag1')).save() 
        PostService mock = Mock(PostService)              
        mock.findByTagName(_) >> [post1]       
        return mock
    }
}
