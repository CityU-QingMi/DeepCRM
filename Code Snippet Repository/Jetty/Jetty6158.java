    private Class<?> getEncoderType(Class<? extends Encoder> encoder, Class<?> interfaceClass)
    {
        Class<?> decoderClass = ReflectUtils.findGenericClassFor(encoder,interfaceClass);
        if (decoderClass == null)
        {
            StringBuilder err = new StringBuilder();
            err.append("Invalid type declared for interface ");
            err.append(interfaceClass.getName());
            err.append(" on class ");
            err.append(encoder);
            throw new InvalidWebSocketException(err.toString());
        }
        return decoderClass;
    }
