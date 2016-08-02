package blagger

import grails.test.mixin.TestFor
import spock.lang.*
import grails.test.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@Unroll
@TestFor(Tag)
class TagSpec extends Specification {

	def tag = new Tag(name: 'test1')

	def 'when valid tag then validation passes'() {
		expect:
		tag.validate()
	}

	def 'when tag name is less then 3 characters then fail validation'(){
		given:
		tag.name = 'aa'

		expect:
		!tag.validate()
        tag.hasErrors()
        tag.errors['name'].code == 'minSize.notmet'
 
	}

	def 'when tag name is empty then fail validation'(){
		given:
		tag.name = ''

		expect:
		!tag.validate()
        tag.hasErrors()
        tag.errors['name'].code == 'blank'
	}
}