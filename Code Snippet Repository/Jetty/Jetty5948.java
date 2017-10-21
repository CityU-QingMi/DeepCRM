    protected Class<?> foundClass(final String name, URL url) throws ClassNotFoundException
    {
        if (_transformers.isEmpty())
            return super.findClass(name);

        InputStream content=null;
        try
        {
            content = url.openStream();
            byte[] bytes = IO.readBytes(content);

            if (LOG.isDebugEnabled())
                LOG.debug("foundClass({}) url={} cl={}",name,url,this);

            for (ClassFileTransformer transformer : _transformers)
            {
                byte[] tmp = transformer.transform(this,name,null,null,bytes);
                if (tmp != null)
                    bytes = tmp;
            }

            return defineClass(name,bytes,0,bytes.length);
        }
        catch (IOException e)
        {
            throw new ClassNotFoundException(name,e);
        }
        catch (IllegalClassFormatException e)
        {
            throw new ClassNotFoundException(name,e);
        }
        finally
        {
            if (content!=null)
            {
                try
                {
                    content.close(); 
                }
                catch (IOException e)
                {
                    throw new ClassNotFoundException(name,e);
                }
            }
        }
    }
