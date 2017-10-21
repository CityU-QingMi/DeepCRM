    protected void populateComponentHtmlId(Form form) {
        String _tmp_id = "";
        if (id != null) {
            // this check is needed for backwards compatibility with 2.1.x
        	_tmp_id = findStringIfAltSyntax(id);
        }
        else {
            if (form != null && form.getParameters().get("id") != null) {
                _tmp_id = _tmp_id + form.getParameters().get("id").toString() + "_";
            }
            if (name != null) {
                _tmp_id = _tmp_id + escape(name);
            } else if (action != null || method != null){
                if (action != null) {
                    _tmp_id = _tmp_id + escape(action);
                }
                if (method != null) {
                    _tmp_id = _tmp_id + "_" + escape(method);
                }
            } else {
                // if form is null, this component is used, without a form, i guess
                // there's not much we could do then.
                if (form != null) {
                    _tmp_id = _tmp_id + form.getSequence();
                }
            }
        }
        addParameter("id", _tmp_id);
    }
