// 모든 select의 속성을 검색한다.각각 검색을해라(each)
$('select[data-value]').each(function(index, el){
	const $el = $(el);
	const defaultValue = $(el).attr('data-value').trim();
	
	if(defaultValue.length > 0){
		$el.val(defaultValue);
	}
})