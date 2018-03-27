package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bed {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bedId;
	@Column
	private boolean vacant;
	@ManyToOne
	@JoinColumn(name = "roomId", foreignKey = @ForeignKey(name = "BedRoomId_FK"))
	private Room room;
	
	public Bed() {}

	public Bed(int bedId, boolean vacant, Room room) {
		super();
		this.bedId = bedId;
		this.vacant = vacant;
		this.room = room;
	}

	public Bed(boolean vacant, Room room) {
		super();
		this.vacant = vacant;
		this.room = room;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bedId;
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + (vacant ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bed other = (Bed) obj;
		if (bedId != other.bedId)
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (vacant != other.vacant)
			return false;
		return true;
	}

	public int getBedId() {
		return bedId;
	}

	public void setBedId(int bedId) {
		this.bedId = bedId;
	}

	public boolean isVacant() {
		return vacant;
	}

	public void setVacant(boolean vacant) {
		this.vacant = vacant;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "Bed [bedId=" + bedId + ", vacant=" + vacant + ", room=" + room + "]";
	}

}
