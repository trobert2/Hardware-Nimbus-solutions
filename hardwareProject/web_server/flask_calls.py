from flask import Flask,request
from hardwareProject.web_server import t2s
# from hardwareProject.GcmServer import ultrasunet
import subprocess
import urllib

app = Flask(__name__)

volume=100

@app.route("/incoming", methods=["POST"])
def hello():
    data = urllib.unquote(request.data).replace('+', ' ').split('&')
    message=data[0].split('=')[1]
    language=str(data[1].split('=')[1])
    if language=='Romanian':
        lang='ro'
    elif language=='Hungarian':
        lang='hu'
    elif language=='French':
        lang='fr'
    else:
        lang='en'

    app.logger.debug(message)
    app.logger.debug(lang)

    t2s.get_tts_mp3(lang, message, 'welcome.mp3')
    bashCommand = "mpg321 -g "+str(volume) +" welcome.mp3"

    # response = ultrasunet.check_distance()
    response = 'OK'
    if response == 'OK':
        process = subprocess.Popen(bashCommand.split(), stdout=subprocess.PIPE)
        output = process.communicate()[0]
        return "all_good"

@app.route("/wat", methods=["GET"])
def hello2():
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

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000, debug=True)
