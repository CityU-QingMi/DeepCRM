        @Override
        public void onComplete(Result result)
        {
            // extract auctions from the results
            Map<String,?> query = (Map<String,?>) JSON.parse(_content.toString());
            Object[] auctions = (Object[]) query.get("Item");
            if (auctions != null)
            {
                for (Object o : auctions)
                    onAuctionFound((Map<String,String>)o);
            }
            onComplete();

        }
