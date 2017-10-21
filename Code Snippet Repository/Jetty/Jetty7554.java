    public static void main(String... args) throws Exception
    {
        Mongo m = new Mongo( "127.0.0.1" , 27017 );
        
        DB db = m.getDB( "mydb" );
        
        Set<String> colls = db.getCollectionNames();

        System.err.println("Colls="+colls);
        
        DBCollection coll = db.getCollection("testCollection");
        

        BasicDBObject key = new BasicDBObject("id","1234");
        BasicDBObject sets = new BasicDBObject("name","value");
        BasicDBObject upsert=new BasicDBObject("$set",sets);
        
        WriteResult result =coll.update(key,upsert,true,false);
        
        System.err.println(result.getLastError());
        
        
        while (coll.count()>0)
        {
            DBObject docZ = coll.findOne();
            System.err.println("removing    "+ docZ);
            if (docZ!=null)
                coll.remove(docZ);
        }
        
     
    }
