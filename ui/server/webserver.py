import SimpleHTTPServer
import SocketServer
import os.path

os.chdir("C:\devel\sal.yo\ui")
PORT = 8000
httpd = SocketServer.TCPServer(("", PORT), SimpleHTTPServer.SimpleHTTPRequestHandler)
httpd.serve_forever()
