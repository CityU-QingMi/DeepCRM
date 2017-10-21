    public WeblogEntryEditor getWeblogEntryEditor(String id) {
        
        WeblogEntryEditor editor = null;
        
        // see if this editor is configured
        editor = (id == null) ? null : (WeblogEntryEditor) this.editors.get(id);
        if(editor == null) {
            editor = this.defaultEditor;
        }
        
        return editor;
    }
