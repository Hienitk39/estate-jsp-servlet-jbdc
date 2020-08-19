<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Danh sach toan nha</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trangchủ</a>
					</li>
					<li>Danh sach san pham</li>

				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12"></div>
					<!-- Search form -->
					<div class="md-form mt-0">
						<input class="form-control" type="text" placeholder="Search"
							aria-label="Search">
					</div>
					<div class="widget-body">
						<div class="widget-main">
							<div class="form-horizontal">
								<div class="form-group">
									<div class="col-sm-6">
										<label>Ten san pham</label>
										<div class="fb-line">
											<input type="text" class="form-control input-sm">
										</div>
									</div>

									<div class="col-sm-6">
										<label>Dien tich san</label>
										<div class="fb-line">
											<input type="number" class="form-control input-sm">
										</div>
									</div>
								</div>
								<div class="form-horizontal">
									<div class="form-group">
										<div class="col-sm-4">
											<label>Ten quan ly</label>
											<div class="fb-line">
												<input type="text" class="form-control input-sm">
											</div>
										</div>

										<div class="col-sm-4">
											<label>Dien thoai quan ly</label>
											<div class="fb-line">
												<input type="number" class="form-control input-sm">
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-3">
												<label for="sel1">Chon nhan vien phu trach</label> <select
													class="form-control" id="sel1">
													<option>1</option>
													<option>2</option>
													<option>3</option>
													<option>4</option>
												</select>
											</div>
										</div>
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-6">
										<label>Loai toa nha</label>
										<div class="fb-line">
											<label class="checkbox-inline"><input type="checkbox"
												value="">Option 1</label> <label class="checkbox-inline"><input
												type="checkbox" value="">Option 2</label> <label
												class="checkbox-inline"><input type="checkbox"
												value="">Option 3</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>

