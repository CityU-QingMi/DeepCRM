    @Override
    public String toString()
    {
        return String.format("%s@%x{%d/%d,%d/%d,%s://:%d,%s}",
                this.getClass().getSimpleName(),
                hashCode(),
                _outputBufferSize, _outputAggregationSize,
                _requestHeaderSize,_responseHeaderSize,
                _secureScheme,_securePort,
                _customizers);
    }
