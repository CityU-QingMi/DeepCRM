    public Class getClassInstance(String className)
            throws ClassNotFoundException {
        PlexusContainer pc = PlexusThreadLocal.getPlexusContainer();

        if (pc == null) {
            pc = base;
        }

        try {
            return pc.lookup(className).getClass();
        }
        catch (Exception e1) {
            try {
                return pc.lookup(Action.class.getName(), className).getClass();
            }
            catch (Exception e2) {
                try {
                    return pc.lookup(Interceptor.class.getName(), className).getClass();
                }
                catch (Exception e3) {
                    try {
                        return pc.lookup(Validator.class.getName(), className).getClass();
                    }
                    catch (Exception e4) {
                        try {
                            return pc.lookup(Result.class.getName(), className).getClass();
                        }
                        catch (Exception e5) {
                            return super.getClassInstance(className);
                        }
                    }
                }
            }
        }
    }
