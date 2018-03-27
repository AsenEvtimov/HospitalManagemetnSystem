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
public class Room {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roomId;
	@ManyToOne
	@JoinColumn(name = "wardId", foreignKey = @ForeignKey(name = "RoomWardId_FK"))
	private Ward ward;
	@ManyToOne
	@JoinColumn(name = "roomTypeId", foreignKey = @ForeignKey(name = "roomTypeId_FK"))
	private RoomCost roomCost;
	
	public Room() {}

	public Room(int roomId, Ward ward, RoomCost roomCost) {
		super();
		this.roomId = roomId;
		this.ward = ward;
		this.roomCost = roomCost;
	}

	public Room(Ward ward, RoomCost roomCost) {
		super();
		this.ward = ward;
		this.roomCost = roomCost;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Ward getWard() {
		return ward;
	}

	public void setWard(Ward ward) {
		this.ward = ward;
	}

	public RoomCost getRoomCost() {
		return roomCost;
	}

	public void setRoomCost(RoomCost roomCost) {
		this.roomCost = roomCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roomCost == null) ? 0 : roomCost.hashCode());
		result = prime * result + roomId;
		result = prime * result + ((ward == null) ? 0 : ward.hashCode());
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
		Room other = (Room) obj;
		if (roomCost == null) {
			if (other.roomCost != null)
				return false;
		} else if (!roomCost.equals(other.roomCost))
			return false;
		if (roomId != other.roomId)
			return false;
		if (ward == null) {
			if (other.ward != null)
				return false;
		} else if (!ward.equals(other.ward))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", ward=" + ward + ", roomCost=" + roomCost + "]";
	}

	
}
