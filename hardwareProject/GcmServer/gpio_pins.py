import RPi.GPIO as GPIO
import time

from gcm import GCM
from datetime import datetime
API_KEY = 'AIzaSyCe1SKR17Y_3NPVql7fhJOW6kGpay_bIfg'
gcm = GCM(API_KEY)

now = datetime.now()
data = {'message':'You have a new visitor!', 'date': now.strftime('%c')}
gerry = 'APA91bHzyoacjLlzbGzUIH3_asktUdZpmr86SUe9FcW-IcO5se3hLNLHWcaZZfYBu-25avhrhTpT5J280yzqfLrxQxEFT72f4bbg8G7xZn65weFXOzsyU2jBvuZwzit_qI6LasfImMqcCtwKSPbNlsDLE5poibcNlc0BF3hkNH1QnIrs8Bzztn4'

def push_notification():
	reg_ids = [gerry]

	# response = gcm.json_request(registration_ids=reg_ids, data=data)

	# Extra arguments
	res = gcm.json_request(
	    registration_ids=reg_ids, data=data,
	    collapse_key='uptoyou', delay_while_idle=True, time_to_live=3600
	)


GPIO.setmode(GPIO.BOARD)

while True:
	try:
		GPIO.setup(7, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)
		GPIO.wait_for_edge(7, GPIO.RISING, 20)
		push_notification()
		print "sent"
		time.sleep(1)
		GPIO.cleanup()
	except Exception as e:
		raise e

