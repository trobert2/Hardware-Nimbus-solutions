import RPi.GPIO as GPIO
import random

GPIO.setmode(GPIO.BOARD)

while True:
	try:

		GPIO.setup(7, GPIO.IN)
		GPIO.wait_for_edge(7, GPIO.RISING, 20)
		response = ['You have very smooth hair', 'You are quite strapping.',
			    'I disagree with anyone who disagrees with you.', 
			    'Your smile is breath taking.', 
			    'I am grateful to be blessed by your presence.']
		print random.choice(response)
		GPIO.cleanup()
	except Exception:
		pass
