package rentpath.teamscores.model;

/**
 * Created by jmoralez on 7/23/15.
 */
public class Session {

    int id;
    String session_key;
    int pivotal_project_id;
    String pivotal_project_label;
    String password;
    int current_story_id;
    String pivotal_project_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public int getPivotal_project_id() {
        return pivotal_project_id;
    }

    public void setPivotal_project_id(int pivotal_project_id) {
        this.pivotal_project_id = pivotal_project_id;
    }

    public String getPivotal_project_label() {
        return pivotal_project_label;
    }

    public void setPivotal_project_label(String pivotal_project_label) {
        this.pivotal_project_label = pivotal_project_label;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCurrent_story_id() {
        return current_story_id;
    }

    public void setCurrent_story_id(int current_story_id) {
        this.current_story_id = current_story_id;
    }

    public String getPivotal_project_name() {
        return pivotal_project_name;
    }

    public void setPivotal_project_name(String pivotal_project_name) {
        this.pivotal_project_name = pivotal_project_name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("   id: ");
        sb.append(id);
        sb.append("   session_key: ");
        sb.append(session_key);
        sb.append("   pivotal_project_id: ");
        sb.append(pivotal_project_id);
        sb.append("   pivotal_project_label: ");
        sb.append(pivotal_project_label);
        sb.append("   password: ");
        sb.append(password);
        sb.append("   current_story_id: ");
        sb.append(current_story_id);
        sb.append("   pivotal_project_name: ");
        sb.append(pivotal_project_name);
        sb.append('}');
        return sb.toString();
    }
}
