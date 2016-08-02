package blagger

class TagService {

    /** Returns list of tags with corresponding number of tags*/
    def getTagsWithCount(){

        def c = Tag.createCriteria()
        def tagsCount = c.list {
            projections {
                groupProperty("name")
                count "name"
            }
        }
        return tagsCount
    }

    /** Returns just tag names without any more details*/
    def getAllTagsNamesOnly(){
        def tags = []
        Tag.list().unique{it.name}
        .each{ 
            tag -> tags.add(tag.name)
        }
        return tags 
    }
}
