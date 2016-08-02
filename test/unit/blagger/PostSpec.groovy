package blagger

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

@TestFor(Post)
@Unroll
class PostSpec extends Specification {

    def post = new Post(title: 'Title', email: 'test@test.com', content: 'Content')

    void 'should successfully validate post'() {
        expect:
        post.validate()
    }

    void 'should be invalid if title with less than 3 characters is provided'() {
        given:
        post.title = invalidTitle

        expect:
        !post.validate()

        where:
        invalidTitle << [null, '', 'Ti']
    }

    void 'should be invalid if valid email is not provided'() {
        given:
        post.email = invalidEmail

        expect:
        !post.validate()

        where:
        invalidEmail << [null, '', 'test@']
    }

    void 'should be invalid if content is not provided'() {
        given:
        post.content = invalidContent

        expect:
        !post.validate()

        where:
        invalidContent << [null, '']
    }
}
