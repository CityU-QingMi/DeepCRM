        public boolean isManageable()
        {
            switch(_managed)
            {
                case MANAGED:
                    return true;
                case AUTO:
                    return _bean instanceof LifeCycle && ((LifeCycle)_bean).isStopped();
                default:
                    return false;
            }
        }
