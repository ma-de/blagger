package blagger

import grails.converters.JSON

class TagController {

    TagService tagService

    def list() {    	        
        def tags = Tag.list()
        [tags: tags, total: tags.size()]
    }

    def all() {
    	render tagService.getAllTagsNamesOnly() as JSON
    }
}
