<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<title>${vo.character }</title>
<head>
<link rel="shortcut icon" href="#">
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body style="background-color: #0000FF">
	<table class="table table-sm table-borderless ">
		<tbody>
			<tr>
				<td class="align-top thumb-wrapper" rowspan="5"><img
					src="${vo.image }" width="200" height="200"
					alt="https://developers.google.com/maps/documentation/streetview/images/error-image-generic.png">
					<hr class="mt-5">
					<div style="width: 105px; background-color: white"
						align="center">${vo.dmg }</div></td>
				<td colspan="3" class="text-left font-weight-bold name-info"><span
					class="name-wrapper"><h2>${vo.character }</h2> </span>
					<div class="sound">
						<audio id="audio" src="${vo.sound }">
						</audio>
						<audio controls onloadstart="this.volume=0.1">
							<source src="${vo.sound }" type="audio/mpeg">
						</audio>
					</div></td>
			</tr>
			<tr>
				<td colspan="4" style="font-size: 1.5em; color: black;">${vo.comb }</td>
			</tr>
		</tbody>
		<tr>
			<td colspan="4">
				<button
					class="btn btn-lg btn-outline-primary font-weight-bold skill-expand-button"
					type="button" data-toggle="collapse" data-target="#skill"
					aria-expanded="false" aria-controls="skill299">스킬</button>
			</td>
		</tr>
	</table>
	<div class="collapse skill-card-wrapper text-left" id="skill">
		<div class="card card-body skill-card mb-2">
			<div class="panel panel-success">
				<div class="panel-heading">${vo.skill }</div>
			</div>
		</div>
	</div>
</body>
</html>