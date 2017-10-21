        public Setter(String propertyName, Method method)
        {
            _propertyName = propertyName;
            _setter = method;
            _type = method.getParameterTypes()[0];
            _numberType = __numberTypes.get(_type);
            if(_numberType==null && _type.isArray())
            {
                _componentType = _type.getComponentType();
                _numberType = __numberTypes.get(_componentType);
            }
        }
