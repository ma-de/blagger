package blagger

class Tag {

	String name

	static belongsTo = Post

	static hasMany = [posts: Post]

    static constraints = {
    	name blank: false, minSize: 3
    }
}
