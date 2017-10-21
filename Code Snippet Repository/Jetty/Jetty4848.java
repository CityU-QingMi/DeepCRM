        public void push(N node)
        {
            while(true)
            {
                Node top = stack.get();
                node.next = top;
                if (stack.compareAndSet(top,node))
                    break;
            }
        }
