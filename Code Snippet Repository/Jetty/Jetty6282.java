        @Override
        public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response)
        {
            int upgradeNum = upgradeCount.addAndGet(1);
            LOG.debug("Upgrade Num: {}", upgradeNum);
            sec.getUserProperties().put("upgradeNum", Integer.toString(upgradeNum));
            switch (upgradeNum)
            {
                case 1:
                    sec.getUserProperties().put("apple", "fruit from tree");
                    break;
                case 2:
                    sec.getUserProperties().put("blueberry", "fruit from bush");
                    break;
                case 3:
                    sec.getUserProperties().put("strawberry", "fruit from annual");
                    break;
                default:
                    sec.getUserProperties().put("fruit" + upgradeNum, "placeholder");
                    break;
            }
            
            super.modifyHandshake(sec, request, response);
        }
