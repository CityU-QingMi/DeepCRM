    private void doConfigure()
    {
        _beanFactory.registerSingleton("properties", _configuration.getProperties());

        // Look for the main bean;
        for (String bean : _beanFactory.getBeanDefinitionNames())
        {
            LOG.debug("{} - {}", bean, Arrays.asList(_beanFactory.getAliases(bean)));
            String[] aliases = _beanFactory.getAliases(bean);
            if ("Main".equals(bean) || aliases != null && Arrays.asList(aliases).contains("Main"))
            {
                _main = bean;
                break;
            }
        }
        if (_main == null)
            _main = _beanFactory.getBeanDefinitionNames()[0];

        // Register id beans as singletons
        Map<String, Object> idMap = _configuration.getIdMap();
        LOG.debug("idMap {}", idMap);
        for (String id : idMap.keySet())
        {
            LOG.debug("register {}", id);
            _beanFactory.registerSingleton(id, idMap.get(id));
        }

        // Apply configuration to existing singletons
        for (String id : idMap.keySet())
        {
            if (_beanFactory.containsBeanDefinition(id))
            {
                LOG.debug("reconfigure {}", id);
                _beanFactory.configureBean(idMap.get(id), id);
            }
        }

        // Extract id's for next time.
        for (String id : _beanFactory.getSingletonNames())
            idMap.put(id, _beanFactory.getBean(id));
    }
