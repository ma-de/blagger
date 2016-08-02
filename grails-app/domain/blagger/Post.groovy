package blagger

class Post {

    String title
    String email
    String content
    Date dateCreated
    Date lastUpdated
    Tag tag

    static constraints = {
        title minSize: 3, blank: false
        email email: true, blank: false
        content blank: false
        tag nullable: true
    }

    static mapping = {
        content type: 'text'
    }
}