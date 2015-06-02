package ditl.sim.tactical;

import ditl.sim.*;
import java.util.Set;

public class GroupMessage extends BroadcastMessage {

	protected Integer _gid; 
	
	public GroupMessage(Router from, long bytes, long creationTime,
			long expirationTime, Integer gid) {
		super(from, null, bytes, creationTime, expirationTime);
		_gid = gid;
	}
	
	public Integer gid(){
		return _gid;
	}
	
	public static final class Factory extends MessageFactory<BroadcastMessage> {
		private World _world;
		public Factory(World world, long minBytes, long maxBytes){
			super(minBytes,maxBytes);
			_world = world;
		}
		@Override
		public BroadcastMessage getNew(long creationTime, long expirationTime) {
			Node from = (Node)_world.getRandomRouter();
			return new GroupMessage(from, nextBytes(), creationTime, expirationTime, from.gid()); 
		}

        @Override
        public Set<Router> defineMessageRecipients() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
	}
}
