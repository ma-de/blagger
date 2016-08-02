package blagger

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TagService)
@Mock([Tag])
class TagServiceSpec extends Specification {

	TagService tagService

	def setup() {
		tagService = new TagService()
	}


	void "test map with tags service"() {
		given:		
		tagService.metaClass.getTagsWithCount = { -> return ['tag1':1] }

        when:
        def tagsWithCount = tagService.getTagsWithCount() 

        then:
        tagsWithCount.size() == 1
        tagsWithCount == ['tag1':1] 
	}

	void "test get list of all tags just names"(){
		given:
		new Tag(name: 'test1').save()
		new Tag(name: 'test2').save()

		when:
		def tags = tagService.getAllTagsNamesOnly()

		then:
		tags
		tags.size() == 2
		tags[0] == 'test1'
		tags[1] == 'test2'
	}
}
