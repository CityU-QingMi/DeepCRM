    private Class<?> getDecoderType(Class<? extends Decoder> decoder, Class<?> interfaceClass)
    {
        Class<?> decoderClass = ReflectUtils.findGenericClassFor(decoder,interfaceClass);
        if (decoderClass == null)
        {
            StringBuilder err = new StringBuilder();
            err.append("Invalid type declared for interface ");
            err.append(interfaceClass.getName());
            err.append(" on class ");
            err.append(decoder);
            throw new InvalidWebSocketException(err.toString());
        }
        return decoderClass;
    }
