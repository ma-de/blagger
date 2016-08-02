package blagger

class PostController {

    PostService postService
    TagService tagService

    def index() {
        def posts = postService.getAllPosts(params)        
        [posts: posts, total: posts.size(), tags: tagService.getTagsWithCount()]
    }

    def create() {
        [post: new Post(params)]
    }

    def list() {       
        def posts = postService.findByTagName(params.id)
        render(view: "index", model: [posts: posts, total: posts?.size(), searchTag: params.id, tags: tagService.getTagsWithCount()])
    }

    def savePost() {
        def newPost = new Post(params)

        if (!newPost.save(flush: true)) {
            render(view: 'create', model: [post: newPost])
            return
        }

        redirect(action: 'index')
    }

}