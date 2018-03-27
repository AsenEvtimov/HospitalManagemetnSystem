package model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RoomCost {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int roomTypeId;
	@Column
	private String roomType;
	@Column
	private BigDecimal cost;
	
	public RoomCost() {}
	

	
	public RoomCost(String roomType, BigDecimal cost) {
		super();
		this.roomType = roomType;
		this.cost = cost;
	}



	public int getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(int roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((roomType == null) ? 0 : roomType.hashCode());
		result = prime * result + roomTypeId;
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
		RoomCost other = (RoomCost) obj;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (roomType == null) {
			if (other.roomType != null)
				return false;
		} else if (!roomType.equals(other.roomType))
			return false;
		if (roomTypeId != other.roomTypeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoomCost [roomTypeId=" + roomTypeId + ", roomType=" + roomType + ", cost=" + cost + "]";
	}

	
}
