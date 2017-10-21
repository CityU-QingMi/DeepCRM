    public static void loadModels(String modelsString, Map<String, Object> modelMap,
                                   Map initData, boolean fail) 
            throws WebloggerException {
        
        String[] models = Utilities.stringToStringArray(modelsString, ",");
        if (models != null) {
            for (String model : models) {
                try {
                    Class modelClass = Class.forName(model);
                    Model pageModel = (Model) modelClass.newInstance();
                    pageModel.init(initData);
                    modelMap.put(pageModel.getModelName(), pageModel);
                } catch (WebloggerException re) {
                    if(fail) {
                        throw re;
                    } else {
                        log.warn("Error initializing model: " + model);
                    }
                } catch (ClassNotFoundException cnfe) {
                    if(fail) {
                        throw new WebloggerException("Error finding model: " + model, cnfe);
                    } else {
                        log.warn("Error finding model: " + model);
                    }
                } catch (InstantiationException ie) {
                    if(fail) {
                        throw new WebloggerException("Error instantiating model: " + model, ie);
                    } else {
                        log.warn("Error instantiating model: " + model);
                    }
                } catch (IllegalAccessException iae) {
                    if(fail) {
                        throw new WebloggerException("Error accessing model: " + model, iae);
                    } else {
                        log.warn("Error accessing model: " + model);
                    }
                }
            }
        }
    }
