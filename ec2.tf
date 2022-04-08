resource "aws_instance" "dh_terraform" {
  count         = 3
  ami           = "ami-01f87c43e618bf8f0"
  instance_type = "t2.micro"
  tags = {
    Name        = "dh_terraform_${count.index}"
    Terraform   = "verdade"
    Environment = "dev"
  }
}

resource "aws_security_group" "permitir_ssh" {
  name        = "permitir_ssh"
  description = "Permite SSH"
  ingress {
    description = "SSH to EC2"
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "permitir_ssh"
  }
}
