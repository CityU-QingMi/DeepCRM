    protected void startClient()//Realm realm)
        throws Exception
    {
        _client = new HttpClient();
        //_client.setConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL);
        //if (realm != null){
//            _client.setRealmResolver(new SimpleRealmResolver(realm));
        //}
        _client.start();
    }
