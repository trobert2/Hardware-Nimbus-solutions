#import RPi.GPIO as GPIO
import time

from gcm import GCM
from datetime import datetime
API_KEY = 'AIzaSyAzubcLXlqRGCrY5f9jxhDs5KdOXZiNt_8'
gcm = GCM(API_KEY)

now = datetime.now()
data = {'message': 'You have a new visitor!', 'date': now.strftime('%c')}
key = 'APA91bEDHpeS7OqwNjY-39TV2ip_3NW2KHeICHx7QQUKxNgIf2qfonV-n4RfqLjeNLB-1BJaPDDtqZUxZXlsC3ncPaRg0mJsXtG4atNLCATSKXm0wGTlMVDeKlQ1ABDBsHAUVrvfbzHsjd5DcDKKEpzrCDqjlLbraQ'


def push_notification():
    reg_ids = [key]

    # Extra arguments
    res = gcm.json_request(
        registration_ids=reg_ids, data=data,
        collapse_key='testing', delay_while_idle=True, time_to_live=3600
    )
    print res

#GPIO.setmode(GPIO.BOARD)

push_notification()
#while True:
#    try:
#        GPIO.setup(7, GPIO.IN, pull_up_down=GPIO.PUD_DOWN)
#       	GPIO.wait_for_edge(7, GPIO.RISING, 20)
#       	push_notification()
#       	print "sent"
#       	time.sleep(1)
#       	GPIO.cleanup()
#    except Exception as e:
#        raise e
#
