from flask import Flask,request
from hardwareProject.web_server import t2s
from hardwareProject.GcmServer import ultrasunet
import subprocess

app = Flask(__name__)

volume=100
@app.route("/incoming6", methods=["POST"])
def hello6():
#    print request.args['message']
    print request
#    data = request.get_json(force=True)
#    print data
    lang='en'
    message='Thanks for having us, now get away from my door!!'
    t2s.get_tts_mp3(lang, message, 'welcome.mp3')
    bashCommand = "mpg321 -g "+str(volume)+" welcome.mp3"
    
#    response = ultrasunet.check_distance()
#    if response == 'OK':
    process = subprocess.Popen(bashCommand.split(), stdout=subprocess.PIPE)
    output = process.communicate()[0]


@app.route("/incoming", methods=["POST"])
def hello3():
#    print request.args['message']
    print request
#    data = request.get_json(force=True)
#    print data
    lang='en'
    message='Gone hacking at HackTM!'
    t2s.get_tts_mp3(lang, message, 'welcome.mp3')
    bashCommand = "mpg321 -g "+str(volume) +" welcome.mp3"
    
    response = ultrasunet.check_distance()
    if response == 'OK':
        process = subprocess.Popen(bashCommand.split(), stdout=subprocess.PIPE)
        output = process.communicate()[0]

@app.route("/incoming2", methods=["POST"])
def hello4():
#    print request.args['message']
    print request
#    data = request.get_json(force=True)
#    print data
    lang='ro'
    message='Plecat la HackTM!'
    t2s.get_tts_mp3(lang, message, 'welcome.mp3')
    bashCommand = "mpg321 -g "+ str(volume) +" welcome.mp3"
    
    response = ultrasunet.check_distance()
    if response == 'OK':
        process = subprocess.Popen(bashCommand.split(), stdout=subprocess.PIPE)
        output = process.communicate()[0]

@app.route("/incoming3", methods=["POST"])
def hello5():
#    print request.args['message']
    print request
#    data = request.get_json(force=True)
#    print data
    lang='hu'
    message='El mentem HakatonTM!'
    t2s.get_tts_mp3(lang, message, 'welcome.mp3')
    bashCommand = "mpg321 -g "+str(volume) +"  welcome.mp3"
    
    response = ultrasunet.check_distance()
    if response == 'OK':
        process = subprocess.Popen(bashCommand.split(), stdout=subprocess.PIPE)
        output = process.communicate()[0]


@app.route("/wat", methods=["GET"])
def hello2():
    print request
#    data = request.get_json(force=True)
#    print data
    lang='en'
    message='Gone hacking at HackTM!'
    t2s.get_tts_mp3(lang, message, 'welcome.mp3')
    bashCommand = "mpg321 -g 100 welcome.mp3"
    
#    response = ultrasunet.check_distance()
#    if response == 'OK':
    process = subprocess.Popen(bashCommand.split(), stdout=subprocess.PIPE)
    output = process.communicate()[0]

@app.route("/incomingqqq", methods=["POST"])
def hello():
    print request
    data = request.get_json(force=True)
    print data
    lang='hu'
    t2s.get_tts_mp3(data['lang'], data['message'], 'welcome.mp3')
    bashCommand = "mpg321 -g 100 welcome.mp3"
    
#    response = ultrasunet.check_distance()
#    if response == 'OK':
    process = subprocess.Popen(bashCommand.split(), stdout=subprocess.PIPE)
    output = process.communicate()[0]
    
if __name__ == "__main__":
    app.run(host='0.0.0.0')
