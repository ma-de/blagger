package blagger

import spock.lang.Unroll
import grails.plugin.spock.IntegrationSpec

class PostIntegrationSpec extends IntegrationSpec {

    PostController controller

    void setup(){
        controller = new PostController()
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
        posts[1].title == 'test'
        posts[1].email == 'test@test.com'
        posts[1].content == 'test'
        posts[1].tag.name == 'test1'        
    }


    def 'List of all post filtered by given tag name'(){
        given:
        new Post(title: 'test1', email: 'test@test.com', content: 'test', tag: new Tag(name: 'tag1')).save() 
        new Post(title: 'test2', email: 'test@test.com', content: 'test', tag: new Tag(name: 'tag2')).save() 
        controller.params.id = 'tag1'

        when:
        controller.list()
        def model = controller.modelAndView.model
        
        then:
        model.posts
        model.posts[0].title == 'test1'
        model.posts[0].tag.name == 'tag1'
    }


    @Unroll("Search for tag name: #tagName returns expected #numberOfPosts posts")
    def 'Check if number of post corresponds searched tag name'(){
        given:
        new Post(title: 'test', email: 'test@test.com', content: 'test', tag: new Tag(name: 'tag1')).save() 
        new Post(title: 'test', email: 'test@test.com', content: 'test', tag: new Tag(name: 'tag2')).save() 
        new Post(title: 'test', email: 'test@test.com', content: 'test', tag: new Tag(name: 'tag4')).save() 
        new Post(title: 'test', email: 'test@test.com', content: 'test', tag: new Tag(name: 'tag1')).save() 
        new Post(title: 'test', email: 'test@test.com', content: 'test', tag: new Tag(name: 'tag3')).save()         
        new Post(title: 'test', email: 'test@test.com', content: 'test', tag: new Tag(name: 'tag3')).save() 
        new Post(title: 'test', email: 'test@test.com', content: 'test', tag: new Tag(name: 'tag3')).save() 
        controller.params.id = tagName

        when:
        controller.list()
        def model = controller.modelAndView.model
        
        then:
        model.posts.size() == numberOfPosts

        where:
        tagName | numberOfPosts 
        'tag1' | 2 
        'tag2' | 1 
        'tag3' | 3 
        'tag4' | 1 
    }
}