class AppBase{
    static DOMAIN_SERVER = location.origin;
    static API_SERVER = this.DOMAIN_SERVER + '/api';
    static API_USERS = this.API_SERVER + '/users';
    static CLOUDINARY_URL = this.SERVER_CLOUDINARY + this.CLOUDINARY_NAME + '/image/upload';
    static API_LOGGING_USER = this.API_USERS + '/index';

    static AlertMessageEn = class {
        static SUCCESS_CREATED = "Successful data generation !";
        static SUCCESS_UPDATED = "Data update successful !";
        static SUCCESS_DEPOSIT = "Deposit transaction successful !";
        static SUCCESS_WITHDRAW = "Withdrawal transaction successful !";
        static SUCCESS_TRANSFER = "Transfer transaction successful !";
        static SUCCESS_DEACTIVATE = "Deactivate the customer successfully !";

        static ERROR_400 = "The operation failed, please check the data again.";
        static ERROR_401 = "Unauthorized - Your access token has expired or is not valid.";
        static ERROR_403 = "Forbidden - You are not authorized to access this resource.";
        static ERROR_404 = "Not Found - The resource has been removed or does not exist";
        static ERROR_500 = "Internal Server Error - The server system is having problems or cannot be accessed.";

        static ERROR_CREATED = 'Adding new customer failed';

        static ERROR_LOADING_PROVINCE = "Loading list of provinces - cities failed !";
        static ERROR_LOADING_DISTRICT = "Loading list of district - ward failed !"
        static ERROR_LOADING_WARD = "Loading list of wards - communes - towns failed !";

        static ERROR_LOADING_PRODUCT = "Loading list of products failed !"
    }

    static AlertMessageVi = class {
        static SUCCESS_CREATED = "Tạo dữ liệu thành công !";
        static SUCCESS_UPDATED = "Cập nhật dữ liệu thành công !";
        static SUCCESS_DELETED = "Xóa dữ liệu thành công !";
        static SUCCESS_DEACTIVATE = "Hủy kích hoạt khách hàng thành công !";

        static ERROR_400 = "Thao tác không thành công, vui lòng kiểm tra lại dữ liệu.";
        static ERROR_401 = "Unauthorized - Access Token của bạn hết hạn hoặc không hợp lệ.";
        static ERROR_403 = "Forbidden - Bạn không được quyền truy cập tài nguyên này.";
        static ERROR_404 = "Not Found - Tài nguyên này đã bị xóa hoặc không tồn tại";
        static ERROR_500 = "Internal Server Error - Hệ thống Server đang có vấn đề hoặc không truy cập được.";

        static ERROR_DELETED = 'Xóa không thành công';


    }

    static showSuspendedConfirmDialog() {
        return Swal.fire({
            icon: 'warning',
            text: 'Are you sure to suspend the selected customer ?',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, please suspend this client !',
            cancelButtonText: 'Cancel',
        })
    }
    static showDeleteCartItemConfirmDialog() {
        return  Swal.fire({
            icon: 'warning',
            text: 'Bạn muốn xóa sản phẩm này ?',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Đồng ý',
            cancelButtonText: 'Không xóa',
        })
    }
    static showSuccessAlert(t) {
        Swal.fire({
            icon: 'success',
            title: t,
            position: 'center',
            showConfirmButton: false,
            timer: 1500
        })
    }

    static showErrorAlert(t) {
        Swal.fire({
            icon: 'error',
            title: 'Warning',
            position: 'center',
            text: t,
        })
    }

    static IziToast = class {
        static showSuccessAlert(m) {
            iziToast.success({
                title: 'OK',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }
        static showErrorAlert(m) {
            iziToast.error({
                title: 'Error',
                position: 'topRight',
                timeout: 2500,
                message: m
            });
        }
    }

    static formDate(date) {
        if(!date) return "";
        let arr = date.split("-")
        date = arr[2] + "-" + arr[1] + "-" + arr[0]
        return date;
    }
    static formMonth(month){
        let arr = month.split("-")
        month =  arr[1] + "-" + arr[0]
        return month;
    }


    static renderProduct(item) {
        if (item.username != "Admin") {
            return ` <tr id="tr_${item.id}" data-id="${item.id}">
                   
                                    <td>
                                      <div class="d-flex ">
                                        <img src="images/faces/face1.jpg" alt="">
                                        <div>
                                          <h6>${item.username}</h6>
                                          <p>Head admin</p>
                                        </div>
                                      </div>
                                    </td>
                                    <td>
                                      <h6>Thiên An Phát</h6>
                                    </td>
                                    <td>
                                      <div>
                                        <div class="d-flex justify-content-between align-items-center mb-1 max-width-progress-wrap">
                                          <p class="text-success">79%</p>
                                          <p>85/162</p>
                                        </div>
                                        <div class="progress progress-md">
                                          <div class="progress-bar bg-success" role="progressbar" style="width: 85%" aria-valuenow="25" aria-valuemin="0" aria-valuemax="100"></div>
                                        </div>
                                      </div>
                                    </td>
                                    <td>
                                    <button class="btn btn-danger btn-lg text-white mb-0 me-0 delete" type="button">Delete</button>
</td>
                                  </tr>`;
        }

    }





// class CartDetail{
//     constructor(id,productsName,color,size,quantity) {
//         this.id = id;
//         this.productId = productsName;
//         this.color = color;
//         this.size = size;
//         // this.price = price;
//         this.quantity = quantity;
//
//     }
}



