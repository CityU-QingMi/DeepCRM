    private void unbind(String name) {
        InitialContext rootCtx = null;
        try {
            rootCtx = new InitialContext();
            rootCtx.unbind(name);
            NonSerializableFactory.unbind(name);
        } catch (NamingException e) {
            log.warn("Failed to unbind scheduler with jndiName: " + name, e); 
        } finally {
            if (rootCtx != null) { 
                try { 
                    rootCtx.close(); 
                } catch (NamingException ignore) {} 
            }
        }
    }
