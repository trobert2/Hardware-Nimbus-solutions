from gcm import GCM
API_KEY = 'AIzaSyCe1SKR17Y_3NPVql7fhJOW6kGpay_bIfg'
gcm = GCM(API_KEY)
data = {'message':'You have a new visitor!', 'date': 'azi'}


# JSON request
req_id = 'super_secret_stuff_totally_not_this_11APA91bHzyoacjLlzbGzUIH3_asktUdZpmr86SUe9FcW-IcO5se3hLNLHWcaZZfYBu-25avhrhTpT5J280yzqfLrxQxEFT72f4bbg8G7xZn65weFXOzsyU2jBvuZwzit_qI6LasfImMqcCtwKSPbNlsDLE5poibcNlc0BF3hkNH1QnIrs8Bzztn4'
reg_ids = [req_id]

response = gcm.json_request(registration_ids=reg_ids, data=data)

# Extra arguments
res = gcm.json_request(
    registration_ids=reg_ids, data=data,
    collapse_key='uptoyou', delay_while_idle=True, time_to_live=3600
)
