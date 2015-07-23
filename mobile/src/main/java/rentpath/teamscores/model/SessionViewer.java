package rentpath.teamscores.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jmoralez on 7/22/15.
 */
public class SessionViewer implements Parcelable {

    private int id;
    private String viewer_name; // viewer_name
    private int scoring_session_id; // scoring_session_id
    private String created_at; // created_at
    private String updated_at; // updated_at

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getViewerName() {
        return viewer_name;
    }

    public void setViewerName(String viewerName) {
        this.viewer_name = viewerName;
    }

    public int getScoringSessionId() {
        return scoring_session_id;
    }

    public void setScoringSessionId(int scoringSessionId) {
        this.scoring_session_id = scoringSessionId;
    }

    public String getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(String createdAt) {
        this.created_at = createdAt;
    }

    public String getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updated_at = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{\n");
        sb.append("   id: ");
        sb.append(id);
        sb.append("\n");
        sb.append("   viewer_name: ");
        sb.append(viewer_name);
        sb.append("\n");
        sb.append("   scoring_session_id: ");
        sb.append(scoring_session_id);
        sb.append("\n");
        sb.append("   created_at: ");
        sb.append(created_at);
        sb.append("\n");
        sb.append("   updated_at: ");
        sb.append(updated_at);
        sb.append("\n");
        return sb.toString();
    }

    public SessionViewer(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.id = Integer.valueOf(data[0]);
        this.viewer_name = data[1];
        this.scoring_session_id = Integer.valueOf(data[2]);
        this.created_at = data[3];
        this.updated_at = data[4];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                Integer.toString(id),
                viewer_name,
                Integer.toString(scoring_session_id),
                created_at,
                updated_at
        });
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public SessionViewer createFromParcel(Parcel in) {
            return new SessionViewer(in);
        }

        public SessionViewer[] newArray(int size) {
            return new SessionViewer[size];
        }
    };
}
