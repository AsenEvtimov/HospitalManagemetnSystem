<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admission</title>
</head>
AdmissionFrom JSP
<body>
	<h2>Admission for Patient:
		${patient.foreName}&nbsp;&nbsp;${patient.surName}</h2>
	<fieldset>
		<legend>Personal Information</legend>
		<form action="PatientServlet?action=updatePatient" method="Post">
			<p>Patient</p>
			<input type="hidden" name="patientId" value="${patient.patientId}" />
			<p>First Name</p>
			<p><input type="text" name="firstName" value="${patient.foreName}" /></p>
			<p>Last Name</p>
			<p><input type="text" name="lastName" value="${patient.surName}"/></p>
			<p>
			<p>Date Of Birth :</p>
			<p><input type="date" name="dob" value="${patient.dateOfBirth}"></p>
			<p>Gender :</p>
			<p><input type="text" name="gender" value="${patient.gender}"></p>
			<p>Address :</p>
			<p><input type="text" name="address" value="${patient.address}"></p>
			<p>Phone Number :</p>
			<p><input type="text" name="phone" value="${patient.phoneNumber}"></p>
			<p>Next Of Kin :</p>
			<p><input type="text" name="kin" value="${patient.nextOfKin}"></p>
			<p>${message}</p>
			<p><input type="submit" value="Update Patient" /></p>
			<br>${message}
			
		</form>
	</fieldset>
		<p><a href="PatientServlet?action=registerNewPatient">Register New Patient</a></p>
	<fieldset>
		<legend>Visit</legend>
		<p>Select Doctor :</p>
		
		<p><a href="DoctorServlet?action=viewAll">View all doctors</a></p>
		
		<form action="PatientServlet?action=searchDoctor" method="Post">
			<p>
				<select name="searchType">
					<option value="id">Search by id</option>
					<option value="lastName">Search by name</option>
				</select> <input type="text" name="searchDoctor" required /> 
				<input type="submit" value="Search" />
					${message1}
			</p>

			<c:if test="${not empty listOfDoctors}">
				<table border="1">
					<tr>
						<th>Id</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Phone Number</th>
						<th>Qualifications</th>
						<th>Department</th>
					</tr>
					<c:forEach var="doctor" items="${listOfDoctors}" varStatus="status">
						<tr>
							<td>${doctor.empId}</td>
							<td>${doctor.firstName}</td>
							<td>${doctor.lastName}</td>
							<td>${doctor.phoneNumber}</td>
							<td>${doctor.qualifications}</td>
							<td>${doctor.department.departmentName}</td>
							<c:set var="departmentId" value="${doctor.department.deptId}"/>
							<c:set var="departmentName" value="${doctor.department.departmentName}"/>
							<c:set var="doctorId" value="${doctor.empId}"/>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			
		</form>
         
         <form action="PatientServlet?action=assignBed&deptId=${departmentId}" method="Post"> 
            <p>department Id: ${departmentId}<br/>
				<a href="BedServlet?action=showRoomsInDepartment&deptId=${departmentId}">Select Room in ${departmentName} Department</a>
			</p>	   			
			<p>
				<select name="roomType">
					<option value="Private">Private</option>
					<option value="Semi-private">Semi-private</option>
					<option value="Public">Public</option>
				</select> 
				<input type="submit" value="Get Bed" />
			</p>
			
			<c:if test="${bed !=null}">
				<table border="1">
					<tr>
						<th>Room No</th>
						<th>Bed No</th>
						<th>Department</th>
						<th>Ward</th>						
						<th>Room Type</th>
						<th>Bed Cost</th>						
					</tr>
					<tr>
						<td>${bed.bedId}</td>
						<td>${bed.room.roomId}</td>
						<td>${bed.room.ward.department.departmentName}</td>
						<td>${bed.room.ward.name}</td>						
						<td>${bed.room.roomCost.roomType}</td>
						<td>${bed.room.roomCost.cost}</td>
					</tr>
			</table>
			</c:if>
		</form>	
		
		action: ${action}<br>
		
		<c:choose>
		<c:when test="${action=='showVisitForUpdate'}">
		<form action="HomeServlet?action=updateVisit" method="Post">
			<p>doctorId: ${doctorId}
						department: ${departmentName}			
		 	<p><input type="hidden" name="doctorId" value = "${doctorId}"></p>
			<p>Admission Date :</p>
			<p><input type="date" name="admissionDate" value = "${admissionDate}"></p>
			<p>Discharge Date :</p>
			<p><input type="date" name="dischargeDate" value = "${dischargeDate}"></p>			
			<p>Appointment :</p>
			<p><input type="date" name="appointmentDate" value = "${appointmentDate}"></p>
			 
          	<label>Inpatient</label>
          	<input type="radio" name="isOutpatient" id="inpatietn" value="false"><br>
          	<label>Outpatient</label>
          	<input type="radio" name="isOutpatient" id="outpatient" value="true"><br>
          		
			<input type="submit" value="Update Visit" /></p>
			<p><a href="PatientServlet?action=registerNewVisit">New Visit for ${patient.foreName}&nbsp;&nbsp;${patient.surName}</a></p>
        	<p><a href="PatientServlet?action=registerNewPatient">Register New Patient</a></p>
        </form>
		</c:when>
		<c:otherwise>
< 		<form action="PatientServlet?action=addVisit&doctorId=${doctorId}" method="Post">
			<p>doctorId: ${doctorId}
						department: ${departmentName}			
		 	<p><input type="hidden" name="doctorId" value = "${doctorId}"></p>
			<p>Admission Date :</p>
			<p><input type="date" name="admissionDate" value = "${admissionDate}"></p>
			<p>Discharge Date :</p>
			<p><input type="date" name="dischargeDate" value = "${dischargeDate}"></p>			
			<p>Appointment :</p>
			<p><input type="date" name="appointmentDate" value = "${appointmentDate}"></p>
			 
          	<label>Inpatient</label>
          	<input type="radio" name="isOutpatient" id="inpatietn" value="false"><br>
          	<label>Outpatient</label>
          	<input type="radio" name="isOutpatient" id="outpatient" value="true"><br>
          		
			<input type="submit" value="Register Visit" /></p>
			<p><a href="PatientServlet?action=registerNewVisit">New Visit for ${patient.foreName}&nbsp;&nbsp;${patient.surName}</a></p>
        	<p><a href="PatientServlet?action=registerNewPatient">Register New Patient</a></p>
        </form>
        </c:otherwise>
        </c:choose>      
	</fieldset>
	
</body>
</html>