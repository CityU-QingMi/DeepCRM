    public void closeOutput() throws IOException
    {
        switch (_outputType)
        {
            case WRITER:
                _writer.close();
                if (!_out.isClosed())
                    _out.close();
                break;
            case STREAM:
                if (!_out.isClosed())
                    getOutputStream().close();
                break;
            default:
                if (!_out.isClosed())
                    _out.close();
        }
    }
