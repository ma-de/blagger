class UrlMappings {

	static mappings = {
		"/$controller/filter-by-tag/$id" {
			controller = 'post'
			action = 'list'
		}

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: 'post', action: 'index')
		"500"(view:'/error')
	}
}
