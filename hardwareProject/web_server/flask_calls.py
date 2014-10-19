from flask import Flask,request
from hardwareProject.web_server import t2s
from hardwareProject.GcmServer import ultrasunet
import subprocess

app = Flask(__name__)

@app.route("/incoming", methods=["POST"])
def hello():
    print request
    data = request.get_json(force=True)
    print data
    lang='hu'
    t2s.get_tts_mp3(data['lang'], data['message'], 'welcome.mp3')
    bashCommand = "mpg321 -g 100 welcome.mp3"
    
    response = ultrasunet.check_distance()
#    if response == 'OK':
    process = subprocess.Popen(bashCommand.split(), stdout=subprocess.PIPE)
    output = process.communicate()[0]
    
if __name__ == "__main__":
    app.run(host='0.0.0.0')
