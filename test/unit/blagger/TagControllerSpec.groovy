package blagger

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(TagController)
@Mock([Tag])
class TagControllerSpec extends Specification {

    void "list of tags is rendered based on the given stem"() {
        given:                
        new Tag(name: 'test').save()

        when:
        def model = controller.list()

        then:
        model.tags
        model.tags[0].name == 'test'
    }

    void "test list of tags as JSON list"(){
        given:
        controller.tagService = createMockTagService()        

        when:
        def model = controller.all()

        then:
		response.json == ['test', 'test2', 'test3']
	}

	/** Mock helper for services */
    private TagService createMockTagService(){
        TagService mock = Mock(TagService)
        mock.getAllTagsNamesOnly() >> ['test', 'test2', 'test3']
        return mock
    }
}