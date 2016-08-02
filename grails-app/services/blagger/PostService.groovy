package blagger

class PostService {


	def getAllPosts(def params){        
		Post.list([sort: 'id', order: 'desc', max: 5] + params)
	}

    def findByTagName(String searchTag) {
    	def query = Post.where {
           tag.name == searchTag
        }

        log.info "searchTag $searchTag"       
        def posts = query.list([sort: 'id', order: 'desc', max: 5])
        log.info "Posts -> $posts"   

        return posts
    }
}
