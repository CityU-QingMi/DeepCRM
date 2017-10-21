    private void loadEntryEditorClasses() {
        
        log.debug("Initializing entry editor plugins");
        
        String editorStr = WebloggerConfig.getProperty("plugins.weblogEntryEditors");
        if (editorStr != null) {
            
            String[] editorList = StringUtils.stripAll(StringUtils.split(editorStr, ","));
            for (int i=0; i < editorList.length; i++) {
                
                log.debug("trying editor " + editorList[i]);
                
                try {
                    Class editorClass = Class.forName(editorList[i]);
                    WeblogEntryEditor editor = (WeblogEntryEditor) editorClass.newInstance();
                    
                    // looks okay, add it to the map
                    this.editors.put(editor.getId(), editor);
                    
                } catch(ClassCastException cce) {
                    log.error("It appears that your editor does not implement "+
                            "the WeblogEntryEditor interface", cce);
                } catch(Exception e) {
                    log.error("Unable to instantiate editor ["+editorList[i]+"]", e);
                }
            }
        }
        
        if(this.editors.size() < 1) {
            log.warn("No entry editors configured, this means that publishing "+
                    "entries will be impossible.");
            return;
        }
        
        // make sure the default editor is defined
        String defaultEditorId = WebloggerConfig.getProperty("plugins.defaultEditor");
        if(defaultEditorId != null) {
            this.defaultEditor = (WeblogEntryEditor) this.editors.get(defaultEditorId);
        }
        
        if(this.defaultEditor == null) {
            // someone didn't configure the default editor properly
            // guess we'll just have to pick one for them
            log.warn("Default editor was not properly configured, picking one at random instead.");
            
            Object editor = this.editors.values().iterator().next();
            this.defaultEditor = (WeblogEntryEditor) editor;
        }
    }
