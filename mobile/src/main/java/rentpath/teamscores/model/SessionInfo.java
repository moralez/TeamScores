package rentpath.teamscores.model;

/**
 * Created by jmoralez on 7/23/15.
 */
public class SessionInfo {

    private Session session;
    private Story story;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("   session: ");
        sb.append(session);
        sb.append("\n");
        sb.append("   story: ");
        sb.append(story);
        sb.append("\n");
        sb.append("}");
        return sb.toString();
    }
}
