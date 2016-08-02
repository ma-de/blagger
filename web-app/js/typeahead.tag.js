/*
* Specific typeahead java script that loads tags from an endpont in JSON format.
* Requires 3 characters to predict 
* Caching is disabled to allow updates in tag list predictions 
*/

var tags = new Bloodhound({
  datumTokenizer: Bloodhound.tokenizers.whitespace,
  queryTokenizer: Bloodhound.tokenizers.whitespace,
  // url points to a json file that contains an array of country names, see
  // https://github.com/twitter/typeahead.js/blob/gh-pages/data/countries.json
  prefetch: {
        url: '../tag/all',
        cache: false //don't cache result so each time get from the server
    }
});

// passing in `null` for the `options` arguments will result in the default
// options being used
$('#prefetch .form-control').typeahead({
  hint: true,
  highlight: true,
  minLength: 3
  }, {
  name: 'tags',
  source: tags
});